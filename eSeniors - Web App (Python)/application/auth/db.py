## Database methods for auth Blueprint

## Import shared model resources
import application.shared.models

## Returns list of all Users within database
def getAllUsers():
    user = application.shared.models.User.query.get(1)
    return user

## Validates user's credentials (exist & correct)
def isValidUser(givenUsername, givenPassword):
    user = application.shared.models.User.query.filter_by(username = givenUsername).first()
    if user and user.check_password(givenPassword):
        return True
    return False

def getUserToken(givenUsername):
    user = application.shared.models.User.query.filter_by(username = givenUsername).first()
    return user.token