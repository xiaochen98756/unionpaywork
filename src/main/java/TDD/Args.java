package TDD;

public class Args {
    private final Schmes schema;
    private final Command command;

    /*
    * Schme解析

    * */
    public Args(String schema, String command) {
        this.schema=new Schmes(schema);
        this.command=new Command(command);
    }

    public Object getValue(String name) {

        return schema.getValue(name,command.getValue(name));
    }
}
