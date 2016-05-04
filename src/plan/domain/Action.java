package plan.domain;

import plan.problem.Problem;

import java.util.LinkedList;

/**
 * Created by LUCA on 01/05/2016.
 *
 * An action in order to start needs the preconditions to be satisfied
 */
public class Action {

    private LinkedList<Effect> effects;
    private LinkedList<Precondition> preconditions;

    /**
     * The constructor takes the basic arguments for defining an action
     * @param preconditions the preconditions
     * @param effects the effects
     */
    public Action(LinkedList<Precondition> preconditions, LinkedList<Effect> effects){
        this.preconditions = preconditions;
        this.effects = effects;
    }

    /**
     * Getter for the preconditions
     * @return the preconditions
     */
    public LinkedList<Precondition> getPreconditions(){
        return preconditions;
    }

    /**
     * Getter for the effects
     * @return the effects
     */
    public LinkedList<Effect> getEffects(){
        return effects;
    }

    /**
     * Determines whether the action is currently applicable, by checking preconditions
     * @param problem the current problem
     * @return true if applicable
     */
    public boolean isApplicable(Problem problem){
        LinkedList<Variable> temp; //will temporary store instance variables of the problem

        for(Precondition precondition : preconditions) {
            temp = problem.getInstanceVariables();
            int index = temp.indexOf(precondition.getVariable());
            Variable tmpVar;

            //check if the precondition variable is declared in the problem
            if(index > -1)
                tmpVar = temp.get(index);
            else
                return false;

            /*
                the first condition checks whether there's an instance of the needed variable in that problem
                to avoid users' mistakes, whereas the second checks whether the current value of the variable
                satisfies the expected value of the precondition
             */
            if(precondition.getVariable().equals(tmpVar) && precondition.isSatisfied())
                continue;
            else
                return false;
        }
        return true;
    }
}
