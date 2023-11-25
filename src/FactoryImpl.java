import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class FactoryImpl implements Factory {

    private Holder first;
    private Holder last;
    private Integer size = 0;


    @java.lang.Override
    public void addFirst(Product product) {
        if (size == 0) {
            Holder x = new Holder(null, product, null);
            first = x;
            last = x;
            size += 1;
        } else {
            Holder Temp = first;
            Holder x = new Holder(null, product, Temp);
            first = x;
            size += 1;
            x.setNextHolder(Temp);
            Temp.setPreviousHolder(x);
        }
    }

    @java.lang.Override
    public void addLast(Product product) {
        if (size == 0) {
            Holder x = new Holder(null, product, null);
            first = x;
            last = x;
            size += 1;
        } else {
            Holder Temp = last;
            Holder x = new Holder(Temp, product, null);
            last = x;
            size += 1;
            x.setPreviousHolder(Temp);
            Temp.setNextHolder(x);
        }

    }

    @java.lang.Override
    public Product removeFirst() throws NoSuchElementException {
        if (size == 0){
            throw new NoSuchElementException();
        } else if (size == 1) {
            Holder Temp = first;
            this.first = null;
            this.last = null;
            size --;
            return Temp.getProduct();
        } else {
            Holder Temp = first;
            Holder new_first = first.getNextHolder();
            new_first.setPreviousHolder(null);
            Temp.setNextHolder(null);
            first = new_first;
            size -= 1;
            return Temp.getProduct();
        }
    }

    @java.lang.Override
    public Product removeLast() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException();
        } else if (size == 1) {
            Holder Temp = first;
            this.first = null;
            this.last = null;
            size --;
            return Temp.getProduct();
        } else {
            Holder Temp = last;
            Holder new_last = last.getPreviousHolder();
            new_last.setNextHolder(null);
            Temp.setPreviousHolder(null);
            last = new_last;
            size --;
            return Temp.getProduct();
        }

    }

    public Holder getFirst() {
        return first;
    }

    public Integer getSize() {
        return size;
    }

    @java.lang.Override
    public Product find(int id) throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        else {
        Holder Temp = first;
        if (size == 1){
            if (first.getProduct().getValue() == id){
                return first.getProduct();
            }
        }
        while (Temp.getNextHolder()!=null){

            int x = Temp.getProduct().getId();
            if (x == id){
                return Temp.getProduct();
            }
            Temp = Temp.getNextHolder();
        }

    }
        throw new NoSuchElementException();
    }

    @java.lang.Override
    public Product update(int id, Integer value) throws NoSuchElementException {
        Product P = find(id);

        if (P!=null){
            Product S = new Product(P.getId(),P.getValue());
            P.setValue(value);
            return S;
        }
        return null;
    }

    @java.lang.Override
    public Product get(int index) throws IndexOutOfBoundsException {
        if (index < size){
            Holder Temp = first;
            for(int i = 0; i < index;i++){
                Temp = Temp.getNextHolder();
            }
            return Temp.getProduct();
        } else{
            throw new IndexOutOfBoundsException();
        }
    }

    @java.lang.Override
    public void add(int index, Product product) throws IndexOutOfBoundsException {
        if ( index==0) {
            addFirst(product);
        } else if (index == size) {
            addLast(product);
        }
        else if (index < size && size>0){
            Holder Temp = first;
            for(int i = 0; i < index;i++){
                Temp = Temp.getNextHolder();
            }
            Holder Prev = Temp.getPreviousHolder();
            Holder x = new Holder(Prev, product, Temp);
            Prev.setNextHolder(x);
            Temp.setPreviousHolder(x);
            size++;
        }  else {
            throw new IndexOutOfBoundsException();
        }
    }

    @java.lang.Override
    public Product removeIndex(int index) throws IndexOutOfBoundsException {
        if(index ==0)return removeFirst();
        if(index==size-1)return removeLast();
        if (index < size) {
            Holder Temp = first;
            for (int i = 0; i < index; i++) {
                Temp = Temp.getNextHolder();
            }
            Temp.getNextHolder().setPreviousHolder(Temp.getPreviousHolder());
            Temp.getPreviousHolder().setNextHolder(Temp.getNextHolder());
            Temp.setNextHolder(null);
            Temp.setPreviousHolder(null);
            size --;
            return Temp.getProduct();
        }
        else throw new IndexOutOfBoundsException();
    }

    @java.lang.Override
    public Product removeProduct(int value) throws NoSuchElementException {
        if(size == 0){
            throw new NoSuchElementException();
        }
        if(value==first.getProduct().getValue())return removeFirst();
        if(value==last.getProduct().getValue())return removeLast();
        Holder Temp = first;
        for (int i = 0; i<size;i++){
            if (Temp.getProduct().getValue()==value) {
                Temp.getNextHolder().setPreviousHolder(Temp.getPreviousHolder());
                Temp.getPreviousHolder().setNextHolder(Temp.getNextHolder());
                Temp.setNextHolder(null);
                Temp.setPreviousHolder(null);
                size --;
                return Temp.getProduct();
            }
            Temp = Temp.getNextHolder();
        }

        throw  new NoSuchElementException();
    }

    @java.lang.Override
    public int filterDuplicates() {
        List<Integer> values = new ArrayList<Integer>();
        int counter = 0;
        Holder Temp = first;
        int oldsize = size;
        int val;
        for(int i = 0; i<oldsize;i++){
            if (values.contains(Temp.getProduct().getValue())){
                val = Temp.getProduct().getValue();
                Temp = Temp.getNextHolder();
                removeIndex(i-counter);
                counter ++;
            } else{
                values.add(Temp.getProduct().getValue());
                Temp = Temp.getNextHolder();
            }

        }
        return counter;
    }
    public String print(){
        String S = "{";
        Holder Temp = first;
        if (Temp != null){
            while (Temp.getNextHolder()!=null ){
                S = S.concat(Temp.getProduct().toString()+',');
                Temp = Temp.getNextHolder();
            }
            S = S.concat(Temp.getProduct().toString()+',');
            S = S.substring(0,S.length()-1);
        }
        //if(size==1) S=S.concat(Temp.getProduct().toString());
        S = S.concat("}\n");
        return S;
    }

    @java.lang.Override
    public void reverse() {
        Holder Temp = last;
        last = first;
        first = Temp;
        for(int i = 0; i<size;i++){
            Holder Temp2 = Temp.getPreviousHolder();
            Temp.setPreviousHolder(Temp.getNextHolder());
            Temp.setNextHolder(Temp2);
            Temp = Temp2;
        }

    }
    }
