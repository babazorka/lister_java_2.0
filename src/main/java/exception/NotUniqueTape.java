package exception;

import imlemented.Tape;

public class NotUniqueTape extends Throwable {
    protected Tape tape;

    public NotUniqueTape(Tape tape) {
        this.tape = tape;
    }

    public Tape getTape() {
        return tape;
    }
}

