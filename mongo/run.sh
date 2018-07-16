docker build -t homework3/mongodb .
docker run --name mongodb -p 27017:27017 -d homework3/mongodb

