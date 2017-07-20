from flask import Flask
from flask import render_template

app = Flask(__name__, static_url_path='')

print('Version 4 - Sogyo.nl Raspberry Pi webserver')
print('Setting up RPi server...')

@app.route('/')
def index():
    return app.send_static_file('index.html')

@app.route('/video')
def parameters():
    return render_template('cameras.html')

if __name__ == '__main__':
    print('Ready.')
    app.run(debug=True, host='0.0.0.0')
