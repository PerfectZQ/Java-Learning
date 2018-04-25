package design_pattern.proxy;

/**
 * 委托类接口
 */
interface SomethingCantDoByYourself {
    // 诉讼
    void litigation();

    // 起草合同
    void draftingContracts();
}

/**
 * 委托类：案件委托人
 */
class CaseClient implements SomethingCantDoByYourself {

    @Override
    public void litigation() {
        System.out.println("打官司");
    }

    @Override
    public void draftingContracts() {
        System.out.println("起草合同");
    }

}

/**
 * 代理类：律师
 */
class Lawyer implements SomethingCantDoByYourself {
    private CaseClient caseClient;

    public Lawyer(CaseClient caseClient) {
        this.caseClient = caseClient;
    }

    @Override
    public void litigation() {
        // 调用委托对象方法
        caseClient.litigation();
        // 扩展功能
        System.out.println("了解案情");
        System.out.println("收集证据");
        System.out.println("辩护");
        System.out.println("打赢官司");
    }

    @Override
    public void draftingContracts() {
        // 调用委托对象方法
        caseClient.draftingContracts();
        // 扩展功能
        System.out.println("审核合同");
        System.out.println("修订合同");
        System.out.println("保证当事人合法利益");
    }
}

public class StaticProxy {

    public static void main(String[] args) {
        Lawyer lawyer = new Lawyer(new CaseClient());
        lawyer.litigation();
        System.out.println("================== 优秀的分割线 ====================");
        lawyer.draftingContracts();
    }
}