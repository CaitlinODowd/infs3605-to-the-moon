
class config(object):
    pass

class devConfig(config):
    pass

class testConfig(config):
    pass

class prodConfig(config):
    db = "TheOzzyConquest.mysql.pythonanywhere-services.com"
    pass

Config = config()