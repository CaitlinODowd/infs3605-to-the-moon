from flask import Blueprint, redirect, url_for
from flask import current_app as app


# Blueprint Configuration
main_bp = Blueprint('main_bp', __name__,
                    template_folder='templates',
                    static_folder='static')

@main_bp.route('/')
def index():
    ## return redirect(url_for('portfolio_bp.index'))
    return "Welcome from Main_BluePrint!"