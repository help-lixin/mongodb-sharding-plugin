package help.lixin.mongodb.plugin.impl;

import help.lixin.mongodb.ctx.TenantContext;
import help.lixin.mongodb.plugin.IMongoDatabaseCustomizer;

public class DefaultMongoDatabaseCustomizer implements IMongoDatabaseCustomizer {
    @Override
    public String customizer(String olddbName) {
        String tenantCode = TenantContext.getTenantCode();
        if (null != tenantCode && !olddbName.endsWith(tenantCode.toLowerCase())) {
            olddbName = olddbName + "_" + tenantCode.toLowerCase();
        }
        return olddbName;
    }
}
