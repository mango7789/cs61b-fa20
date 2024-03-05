public class OffByN implements CharacterComparator{

    int N;

    public OffByN() {
        N = 1;
    }

    public OffByN(int n) {
        N = n;
    }
    
    @Override
    public boolean equalChars(char a, char b) {
        return (Math.abs(((int) a ) - ((int) b)) == N);
    }
}