class DemoServer {

    public static void main(String[] args) throws Exception {

        Server server = ServerBuilder.forPort(6565)
                .addService(new GreeterImpl())
                .build();

        server.start();

        server.awaitTermination();
    }

    static class GreeterImpl extends GreeterGrpc.GreeterImplBase {
        @Override
        public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
            HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
