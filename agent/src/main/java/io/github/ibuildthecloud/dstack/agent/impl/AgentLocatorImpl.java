package io.github.ibuildthecloud.dstack.agent.impl;

import io.github.ibuildthecloud.dstack.agent.AgentLocator;
import io.github.ibuildthecloud.dstack.agent.RemoteAgent;
import io.github.ibuildthecloud.dstack.core.model.Agent;
import io.github.ibuildthecloud.dstack.eventing.EventService;
import io.github.ibuildthecloud.dstack.json.JsonMapper;
import io.github.ibuildthecloud.dstack.object.ObjectManager;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import org.apache.commons.beanutils.PropertyUtils;

public class AgentLocatorImpl implements AgentLocator {

    ObjectManager objectManager;
    EventService eventService;
    JsonMapper jsonMapper;
    ExecutorService executorService;

    @Override
    public RemoteAgent lookupAgent(Object resource) {
        if ( resource == null ) {
            return null;
        }

        Long agentId = null;
        if ( resource instanceof Agent ) {
            return new RemoteAgentImpl(executorService, jsonMapper, eventService, ((Agent)resource).getId());
        }

        if ( agentId == null ) {
            agentId = getAgentId(resource);
        }

        return agentId == null ? null : new RemoteAgentImpl(executorService, jsonMapper, eventService, agentId);
    }

    protected Long getAgentId(Object resource) {
        try {
            Object obj = PropertyUtils.getProperty(resource, "agentId");
            if ( obj instanceof Long ) {
                return (Long)obj;
            }
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        } catch (NoSuchMethodException e) {
        }

        return null;
    }

    public ObjectManager getObjectManager() {
        return objectManager;
    }

    @Inject
    public void setObjectManager(ObjectManager objectManager) {
        this.objectManager = objectManager;
    }

    public EventService getEventService() {
        return eventService;
    }

    @Inject
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    public JsonMapper getJsonMapper() {
        return jsonMapper;
    }

    @Inject
    public void setJsonMapper(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

}