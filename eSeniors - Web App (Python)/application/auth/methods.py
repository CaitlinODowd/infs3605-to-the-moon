
def checkValidJSON(givenJson):
    if 'username' not in givenJson or 'password' not in givenJson:
        return False
    return True