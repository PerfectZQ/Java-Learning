package design_pattern.proxy;

/**
 * 委托类接口：诉讼
 */
interface Litigation {
    // 打官司
    void prosecute();
}

/**
 * 委托类：案件委托人
 */
class CaseClient implements Litigation {

    @Override
    public void prosecute() {
        System.out.println("打官司");
    }
}

/**
 * 代理类：律师
 */
class Lawyer implements Litigation {
    private CaseClient caseClient;

    public Lawyer(CaseClient caseClient) {
        this.caseClient = caseClient;
    }

    @Override
    public void prosecute() {
        // 调用委托对象方法
        caseClient.prosecute();
        // 扩展功能
        System.out.println("了解案情");
        System.out.println("收集证据");
        System.out.println("辩护");
        System.out.println("打赢官司");
    }
}

public class StaticProxyPattern {

    public static void main(String[] args) {
        Lawyer lawyer = new Lawyer(new CaseClient());
        lawyer.prosecute();
    }
}