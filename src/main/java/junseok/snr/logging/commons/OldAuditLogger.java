package junseok.snr.logging.commons;

public interface OldAuditLogger {

    public void log(String uid, String ip);
    public void log(String uid, String ip, String topMenuName);
    public void log(String uid, String ip, String topMenuName, String subMenuName);
    public void log(String uid, String ip, String topMenuName, String subMenuName, Object preData);
    public void log(String uid, String ip, String topMenuName, String subMenuName, Object preData, Object postData);
}