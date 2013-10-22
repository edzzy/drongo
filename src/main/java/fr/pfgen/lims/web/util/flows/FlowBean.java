/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.util.flows;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component
@Scope("session")
public class FlowBean {
    
    private List<FlowType> flowStack = new ArrayList<>();

    public FlowType getLastFlow(){
        if (flowStack.isEmpty()){
            return null;
        }
        return flowStack.get(flowStack.size()-1);
    }
    
    public void removeLastFlow(){
        if (!flowStack.isEmpty()){
            flowStack.remove(flowStack.size()-1);
        }
    }
    
    public FlowType getFlowAbove(FlowType f){
        int i = flowStack.indexOf(f);
        if (i==0){
            return null;
        }else{
            return flowStack.get(i-1);
        }
    }
    
    public void endFlow(FlowType f){
        flowStack.remove(flowStack.lastIndexOf(f));
    }
    
    public void addFlowToStack(FlowType f){
        if (!flowStack.contains(f)){
            flowStack.add(f);
        }
    }
    
    public List<FlowType> getFlowStack() {
        return flowStack;
    }

    public void setFlowStack(List<FlowType> flowStack) {
        this.flowStack = flowStack;
    }
}
