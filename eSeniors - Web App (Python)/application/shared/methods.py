## Shared methods for eSeniors application

import secrets

## Generates user's API token
def generateToken():
    token = secrets.token_urlsafe(64)
    return token

