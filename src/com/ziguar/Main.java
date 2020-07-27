package com.ziguar;

import com.ziguar.create.ComponentContext;
import com.ziguar.create.ComponentContextImpl;
import com.ziguar.create.WorkflowContext;
import com.ziguar.create.component.CreateComponentImpl;
import com.ziguar.create.workflow.CreateWorkFlowImpl;
import com.ziguar.create.workflow.CreateWorkflow;
import com.ziguar.exception.WorkflowException;
import com.ziguar.repository.workflow.WorkflowRepository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Create Workflow - ");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter initial number of components ");
        int count = sc.nextInt();

        List<Component> components = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            String tag = String.valueOf(i+1);
            String componentName = "Component-1."+tag;
            createAndAddComponent(componentName, "Task", components);
        }

        System.out.println("Do you want additional components? (yes/no) => ");
        String moreComponents = sc.nextLine();
        boolean moreComponentsBool = false;
        if(moreComponents.equalsIgnoreCase("yes")) {
            moreComponentsBool = true;
        }

        while(moreComponentsBool) {
            String tag = String.valueOf(count);
            String componentName = "Component-1."+tag;
            createAndAddComponent(componentName, "Task", components);
            count = count+1;
            System.out.println("Do you still want additional components? (yes/no) => ");
            moreComponents = sc.nextLine();
            if(moreComponents.equalsIgnoreCase("yes")) {
                moreComponentsBool = true;
                count = count+1;
            } else {
                moreComponentsBool = false;
            }
        }

        CreateWorkflow workFlow = new CreateWorkFlowImpl.CreateWorkFlowBuilder("Workflow-1")
                .withComponents(components)
                .build();

        try {
            WorkflowContext workflowContext = workFlow.create();
            WorkflowRepository workflowRepository = new WorkflowRepository(workflowContext);

            List<Component> componentsList = workFlow.getComponents();
            List<ComponentContext> componentContexts = new LinkedList<>();
            for(Component component : componentsList) {
                ComponentContext componentContext =
                        new ComponentContextImpl(component.getComponentName(),
                                component.getComponentType(), component.getComponentConfig());
                componentContexts.add(componentContext);
            }
            workflowRepository.persistRecord();
        } catch (WorkflowException e) {
            e.printStackTrace();
        }
    }

    public static void createAndAddComponent(String componentName, String componentType, List<Component> components) {
        Component component = new CreateComponentImpl.CreateComponentBuilder(componentName)
                .withComponentType(componentType)
                .withComponentConfig(null)
                .build();
        components.add(component);
    }
}
