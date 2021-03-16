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
    if not application.auth.methods.checkValidJSON(postReq):
        return '{"Status": "Error", "Error": "Invalid post data"}'

    if application.auth.db.isValidUser(postReq['username'], postReq['password']):
        return '{"Token": "' + application.auth.db.getUserToken(postReq['username']) + '"}'

    return '{"Status": "Error", "Error": "Invalid Username or Password"}'

## showAll route (currently un-used, included for testing)
@auth_bp.route('/showAll/')
def showUsers():
    return str(application.auth.db.getAllUsers())
