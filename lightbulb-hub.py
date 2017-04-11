#!/usr/bin/env python
"""
Very simple lightbulb hub HTTP server (Handles GET requests only).
This is for demoing a mock IoT hub that has a lightbulb.

Usage::
    ./lightbulb-hub.py [<port>]
"""
from BaseHTTPServer import BaseHTTPRequestHandler, HTTPServer
import SocketServer

lightbulb_state = 'ON'

class S(BaseHTTPRequestHandler):
    def _set_headers(self):
        self.send_response(200)
        self.send_header('Content-type', 'application/json')
        self.end_headers()

    def do_GET(self):
        global lightbulb_state
        if self.path.endswith("turn_on"):
            self._set_headers()
            msg = '{"success":"true","state":"ON","prevState":"' + lightbulb_state + '"}\n'
            self.wfile.write(msg)
            lightbulb_state = 'ON'
            print msg
            return
        if self.path.endswith("turn_off"):
            self._set_headers()
            msg = '{"success":"true","state":"OFF","prevState":"' + lightbulb_state + '"}\n'
            self.wfile.write(msg)
            lightbulb_state = 'OFF'
            print msg
            return
        
def run(server_class=HTTPServer, handler_class=S, port=80):
    server_address = ('', port)
    httpd = server_class(server_address, handler_class)
    print 'Starting IoT hub with lightbulb device...'
    httpd.serve_forever()

if __name__ == "__main__":
    from sys import argv

    if len(argv) == 2:
        run(port=int(argv[1]))
    else:
        run()
