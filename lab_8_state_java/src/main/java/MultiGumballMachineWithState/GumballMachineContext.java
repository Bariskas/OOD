package MultiGumballMachineWithState;

public interface GumballMachineContext {
    void setSoldOutState();
    void setNoQuarterState();
    void setHasQuarterState();
    void setSoldState();

    int getGumballCount();
    int getQuarterCount();
    void setGumballCount(int count);
    void addQuarter();
    void removeQuarter();
    void resetQuarterCount();
    void releaseBall();
}
