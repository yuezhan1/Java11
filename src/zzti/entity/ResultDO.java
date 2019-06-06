package zzti.entity;

/**
 * Created by guoli on 17/7/6.
 */
public class ResultDO {
    private String info;
    private boolean success;
    private Object result;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
