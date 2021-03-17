from werkzeug.security import generate_password_hash, check_password_hash

## Shared database models for eSeniors application
from flask_sqlalchemy import SQLAlchemy

## Import shared model resources
import application.shared.methods
##from datetime import datetime

## Define database instance
db = SQLAlchemy()

## User model containing all fields
## Commented out fields are to be implemented late (TODO)
class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(100), nullable=False, unique=True)
    password_hash = db.Column(db.String(128), nullable=False)
    token = db.Column(db.String(100), unique=True, default=application.shared.methods.generateToken)
    ##tokenExpiration = db.Column(db.DateTime, default=datetime.utcnow)
    ##lastLogin = db.Column(db.DateTime, default=datetime.utcnow)

    def set_password(self, password):
        self.password_hash = generate_password_hash(password)

    def check_password(self, password):
        return check_password_hash(self.password_hash, password)

    ## Overload built-in method to correct display object as String type
    def __repr__(self):
        return '<(ID: {}) User "{}" - {} (API-Key: {})>'.format(self.id, self.username, self.password, self.token)