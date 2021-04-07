## Database methods for auth Blueprint

## Import shared model resources
import application.shared.models

## Returns list of all Users within database
def getAllUsers():
    user = application.shared.models.User.query.get(1)
    return user

## Validates user's credentials (exist & correct)
def isValidUser(givenEmail, givenPassword):
    user = application.shared.models.User.query.filter_by(email = givenEmail.lower()).first()
    if user and user.check_password(givenPassword):
        return True
    return False

def isUserExist(givenEmail):
    user = application.shared.models.User.query.filter_by(email = givenEmail.lower()).first()
    if user:
        return True
    return False

def getUser(givenEmail):
    user = application.shared.models.User.query.filter_by(email = givenEmail.lower()).first()
    return user

def insertUser(email, password, DOB, firstName, lastName):
    newUser = application.shared.models.User(email=email, firstName=firstName, lastName=lastName, DOB=DOB)
    newUser.set_password(password)
    application.shared.models.db.session.add(newUser)
    application.shared.models.db.session.commit()