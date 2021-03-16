from flask import Blueprint
from flask import current_app as app


# Blueprint Configuration
admin_bp = Blueprint('admin_bp', __name__,
                    template_folder='templates',
                    static_folder='static')

@admin_bp.route('/')
def index():
    return "Welcome from Admin_BluePrint!"