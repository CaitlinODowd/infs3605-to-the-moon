## Initialize eSeniors Flask application
from flask import Flask

## Import shared model resources
from application.shared.models import db, User

## Method used to bootstrap eSeniors Flask application
def create_app():
    ## Create Flask application
    app = Flask(__name__)
    app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///eSeniors.db'
    #app.config.from_object('config.Config')
    db.init_app(app)

    with app.app_context():
        ## Import parts of our application
        from application.main.routes import main_bp
        from application.admin.routes import admin_bp
        from application.auth.routes import auth_bp

        ## Register Blueprints
        app.register_blueprint(main_bp, url_prefix='/')
        app.register_blueprint(admin_bp, url_prefix='/admin')
        app.register_blueprint(auth_bp, url_prefix='/auth')

        ## Create database tables & user models
        db.create_all()

        ## Populate DB on creation
        ## Attempt to create sample data
        try:
            newUser = User(username='example')
            newUser.set_password('example')
            db.session.add(newUser)
            db.session.commit()

        ## If error raised, allow it to fail gracefully
        except:
            pass

        ## Return eSeniors Flask application instance
        return app