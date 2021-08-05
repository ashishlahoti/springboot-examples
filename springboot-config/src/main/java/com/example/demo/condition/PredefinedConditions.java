package com.example.demo.condition;

import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.autoconfigure.web.server.ConditionalOnManagementPort;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.cloud.CloudPlatform;
import org.springframework.boot.system.JavaVersion;

@ConditionalOnClass(name = "com.example.demo.DoesNotExist")
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnJava(JavaVersion.EIGHT)
@ConditionalOnCloudPlatform(CloudPlatform.KUBERNETES)
@ConditionalOnWarDeployment
@ConditionalOnJndi("java:comp/env/ejb/myEJB")
@ConditionalOnSingleCandidate
@ConditionalOnManagementPort(ManagementPortType.DIFFERENT)
@ConditionalOnAvailableEndpoint(endpoint = InfoEndpoint.class)
@ConditionalOnEnabledHealthIndicator(value = "heartbeat")
public class PredefinedConditions {
}
