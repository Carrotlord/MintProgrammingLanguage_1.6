package builtin.math;

import builtin.BuiltinSub;
import java.math.BigInteger;
import mint.Heap;
import mint.MintException;
import mint.Pointer;
import mint.PointerTools;
import mint.SmartList;

/**
 *
 * @author Oliver Chu
 */
public class Factorial extends BuiltinSub {
    private int factorial(int x) {
        if (x <= 1)
            return 1;
        return x * factorial(x - 1);
    }
    
    private BigInteger factorial(BigInteger x) {
        BigInteger one = new BigInteger("1");
        int cmp = x.compareTo(one);
        if (cmp <= 0)
            return one;
        return x.multiply(factorial(x.subtract(one)));
    }

    @Override
    public Pointer apply(SmartList<Pointer> args) throws MintException {
        Integer operand0 = PointerTools.dereferenceInt(args.get(0));
        if (operand0 == null) {
            BigInteger op0 = PointerTools.dereferenceBigInt(args.get(0));
            if (op0 == null) {
                throw new MintException("Factorial can only be applied to " + 
                                        "integers.");
            }
            return Heap.allocateBigInt(factorial(op0));
        }
        return Heap.allocateInt(factorial(operand0));
    }
    
}
