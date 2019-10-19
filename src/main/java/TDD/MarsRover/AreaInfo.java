package TDD.MarsRover;

import com.sun.istack.internal.NotNull;

public class AreaInfo {
    @NotNull
    private  int length;
    @NotNull
    private  int width;

    public AreaInfo( int length,int width) {
        this.length=length;
        this.width=width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
