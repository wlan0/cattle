package io.github.ibuildthecloud.agent.server.connection;

import java.io.IOException;

import io.github.ibuildthecloud.dstack.core.model.Agent;

public interface AgentConnectionFactory {

    AgentConnection createConnection(Agent agent) throws IOException;

}
