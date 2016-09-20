"# AndroidSocketChatApp" 

use permission in menifest
  uses-permission android:name="android.permission.INTERNET" 

add dependency:-
    compile 'com.android.support:appcompat-v7:23.1.1'
    compile 'com.github.nkzawa:socket.io-client:0.3.0'
    
now add node-modules by entering these commands through cmd/ terminal:-
  >npm init
  (make entry point: index.js)
  >npm install express --save
  >npm install socket.io --save
  to launch the server
  >node index.js
