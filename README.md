# wx-driver-test

>selenium docker test

web自动化测试

#### install

docker run -d --name wxhub -p 4444:4444 selenium/hub

docker run -d -p 5900:5900 --link wxhub:hub --name wxchrome -e NODE_MAX_INSTANCES=5 -e NODE_MAX_SESSION=5 -e NODE_REGISTER_CYCLE=5000 -e DBUS_SESSION_BUS_ADDRESS=/dev/null selenium/node-chrome-debug

#### about
mvn install clean test

PageObject模式，驱动excel、csv文件执行测试用例，VNC(secret)连接端口查看浏览器运行情况
