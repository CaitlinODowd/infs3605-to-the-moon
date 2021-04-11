from werkzeug.security import generate_password_hash, check_password_hash
import json

## Shared database models for eSeniors application
from flask_sqlalchemy import SQLAlchemy
from sqlalchemy.orm import validates

## Import shared model resources
import application.shared.methods
##from datetime import datetime

## Define database instance
db = SQLAlchemy()

## User model containing all fields
## Commented out fields are to be implemented late (TODO)
class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    email = db.Column(db.String(100), nullable=False, unique=True)
    password_hash = db.Column(db.String(128), nullable=False)
    token = db.Column(db.String(100), unique=True, default=application.shared.methods.generateToken)
    firstName = db.Column(db.String(50), nullable=False)
    lastName = db.Column(db.String(50), nullable=False)
    DOB = db.Column(db.String(10), nullable=False)
    ##tokenExpiration = db.Column(db.DateTime, default=datetime.utcnow)
    ##lastLogin = db.Column(db.DateTime, default=datetime.utcnow)

    def set_password(self, password):
        self.password_hash = generate_password_hash(password)

    def check_password(self, password):
        return check_password_hash(self.password_hash, password)

    @validates('email')
    def convert_lower(self, key, value):
        return value.lower()

    ## Converts User data to JSON format
    def toJson(self):
        userJson = {
            'email': self.email,
            'token': self.token,
            'firstName': self.firstName,
            'lastName': self.lastName,
            'DOB': self.DOB
        }

        return json.dumps(userJson)


    ## Overload built-in method to correct display object as String type
    def __repr__(self):
        return '<(ID: {}) User "{}" - {} (API-Key: {})>'.format(self.id, self.email, self.password_hash, self.token)