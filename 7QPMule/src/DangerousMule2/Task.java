package DangerousMule2;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.script.Script;

public abstract class Task {
  // The script instance
  protected Script script;

  public Task(Script script) {
    this.script = script;
  }

  /**
   * @return if this Task should execute.
   */
  public abstract boolean verify();

  /**
   * Executes this Task.
   *
   * @return sleep time after this task ends.
 * @throws Exception 
   */
  

  
  
  
  
  
  
  public abstract int execute() throws Exception;

  /**
   * @return a description of the current Task.
   */
  public abstract String describe();





}