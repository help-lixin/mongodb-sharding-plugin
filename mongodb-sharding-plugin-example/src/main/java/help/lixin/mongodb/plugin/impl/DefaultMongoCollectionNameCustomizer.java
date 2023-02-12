package help.lixin.mongodb.plugin.impl;

import help.lixin.mongodb.ctx.TenantContext;
import help.lixin.mongodb.plugin.IMongoCollectionNameCustomizer;

public class DefaultMongoCollectionNameCustomizer implements IMongoCollectionNameCustomizer {
    @Override
    public String customizer(String oldCollectionName) {
        String tenantCode = TenantContext.getTenantCode();
        if (null != tenantCode && !oldCollectionName.endsWith(tenantCode.toLowerCase())) {
            oldCollectionName = oldCollectionName + "_" + tenantCode.toLowerCase();
        }
        return oldCollectionName;
    }
}
