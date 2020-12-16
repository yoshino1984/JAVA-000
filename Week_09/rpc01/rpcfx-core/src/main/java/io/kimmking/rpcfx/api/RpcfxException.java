package io.kimmking.rpcfx.api;

public class RpcfxException extends RuntimeException{

    static final long serialVersionUID = -7034897190745761239L;

    public RpcfxException() {
        super();
    }

    public RpcfxException(String message) {
        super(message);
    }

    public RpcfxException(String message, Throwable cause) {
        super(message, cause);
    }
}
