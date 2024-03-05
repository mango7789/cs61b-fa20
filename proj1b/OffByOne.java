public class OffByOne implements CharacterComparator{
    
    @Override
    public boolean equalChars(char a, char b) {
        return (Math.abs(((int) a ) - ((int) b)) == 1);
    }
}