## Routes for auth Blueprint

from flask import Blueprint, request
from flask import current_app as app
import application.auth.db, application.auth.methods


# Blueprint configuration
auth_bp = Blueprint('auth_bp', __name__,
                    template_folder='templates',
                    static_folder='static')

## Index route (currently un-used, included for testing)
@auth_bp.route('/')
def index():
    return "Welcome from Auth_BluePrint!"

## Device route (Provides API key upon successful login)
@auth_bp.route('/device/', methods=['POST'])
def deviceLogin():
    postReq = request.json
    if not application.auth.methods.checkValidLoginJSON(postReq):
        return '{"status": "400", "error": "invalid JSON data"}'

    if application.auth.db.isValidUser(postReq['email'], postReq['password']):
        return application.auth.db.getUser(postReq['email']).toJson()

    return '{"status": "403", "error": "invalid email or password"}'

@auth_bp.route('/register/', methods=['POST'])
def registerUser():
    postReq = request.json
    if not application.auth.methods.checkValidRegisterJSON(postReq):
        return '{"status": "400", "error": "invalid JSON data"}'

    if application.auth.db.isUserExist(postReq['email']):
        return '{"status": "404", "error": "Account with that email already exists!"}'

    if not application.auth.methods.isPasswordCorrect(postReq['password'], postReq['confirmPassword']):
        return '{"status": "405", "error": "Passwords do not match!"}'

    application.auth.db.insertUser(postReq['email'], postReq['password'], postReq['DOB'])

    return application.auth.db.getUser(postReq['email']).toJson()


## showAll route (currently un-used, included for testing)
@auth_bp.route('/showAll/')
def showUsers():
    return str(application.auth.db.getAllUsers())
