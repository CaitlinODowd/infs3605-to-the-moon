
def checkValidLoginJSON(givenJson):
    if 'email' not in givenJson or 'password' not in givenJson:
        return False
    return True

def checkValidRegisterJSON(givenJson):
    if 'email' not in givenJson or 'password' not in givenJson or 'confirmPassword' not in givenJson or 'DOB' not in givenJson or 'firstName' not in givenJson or 'lastName' not in givenJson:
        return False
    return True

def isPasswordCorrect(password, confirmPassword):
    if password == confirmPassword:
        return True
    return False