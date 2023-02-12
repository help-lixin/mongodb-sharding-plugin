package help.lixin.mongodb.ctx;

public class TenantContext {

    private static final ThreadLocal<String> TENANT_CTX = new ThreadLocal<>();

    public static String getTenantCode() {
        return TENANT_CTX.get();
    }

    public static void setTenantCode(String tenantCode) {
        TENANT_CTX.set(tenantCode);
    }
}
