/** Represnts a list of musical tracks. The list has a maximum capacity (int),
 *  and an actual size (number of tracks in the list, an int). */
class PlayList {
    private Track[] tracks;  // Array of tracks (Track objects)   
    private int maxSize;     // Maximum number of tracks in the array
    private int size;        // Actual number of tracks in the array

    /** Constructs an empty play list with a maximum number of tracks. */ 
    public PlayList(int maxSize) {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }

    /** Returns the maximum size of this play list. */ 
    public int getMaxSize() {
        return maxSize;
    }
    
    /** Returns the current number of tracks in this play list. */ 
    public int getSize() {
        return size;
    }

    /** Method to get a track by index */
    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    }
    
    /** Appends the given track to the end of this list. 
     *  If the list is full, does nothing and returns false.
     *  Otherwise, appends the track and returns true. */
    public boolean add(Track track) {
        if (this.size == maxSize) {
            return false;
        } else {
            this.tracks[size] = track;
            this.size++;
        }
        return true;
    }

    /** Returns the data of this list, as a string. Each track appears in a separate line. */
    //// For an efficient implementation, use StringBuilder.
    public String toString() {
        String toString = "";
        for (int i = 0; i < this.size; i++) {
            toString += tracks[i].toString() + "\n";
            
        }
        return toString;
    }

    /** Removes the last track from this list. If the list is empty, does nothing. */
     public void removeLast() {
        if (this.size > 0) {
            this.tracks[size - 1] = null;
            this.size--;
        }
            
        
    }
    
    /** Returns the total duration (in seconds) of all the tracks in this list.*/
    public int totalDuration() {
        int sumSeconds = 0;
        for (int i = 0; i < size; i++) {
            sumSeconds += tracks[i].getDuration();
        }
        return sumSeconds;
    }

    /** Returns the index of the track with the given title in this list.
     *  If such a track is not found, returns -1. */
    public int indexOf(String title) {
        String validInput = "";
        if (title.charAt(0) > 'a' ) {
            validInput+= ((char)title.charAt(0) - 32);
        } else {
            validInput += title.charAt(0);
        }
        for (int i = 1; i < title.length(); i++) {
            if (title.charAt(i) < 'a') {
                validInput += ((char)(title.charAt(i) + 32));
            } else {
                validInput += title.charAt(i);
            }
        }

        for (int i = 0; i < size; i++) {
            if (tracks[i].getTitle().equals(validInput)) {
                return i;
            }

        }
        return -1;
    }

    /** Inserts the given track in index i of this list. For example, if the list is
     *  (t5, t3, t1), then just after add(1,t4) the list becomes (t5, t4, t3, t1).
     *  If the list is the empty list (), then just after add(0,t3) it becomes (t3).
     *  If i is negative or greater than the size of this list, or if the list
     *  is full, does nothing and returns false. Otherwise, inserts the track and
     *  returns true. */
    public boolean add(int i, Track track) {
        if ( i < 0 || i >= maxSize || this.size == this.maxSize) {
            return false;
        } else {
            for (int j = size - 1; j >= i ; j--) {
                this.tracks[j + 1] = tracks[j];
            }

            this.tracks[i] = track;
            this.size ++;

            return true;
        }
    }
     
    /** Removes the track in the given index from this list.
     *  If the list is empty, or the given index is negative or too big for this list, 
     *  does nothing and returns -1. */
    public void remove(int i) {
        // if ( size == 0 || i < 0 || i > (size - 1)) {
            
        // } 

       if ( size != 0 && i >= 0 && i <= (size -1)) {
          for (int j = i; j < size ; j++) {
            this.tracks [j] = this.tracks[j + 1];
          }
       }
       this.size --;
    }

    /** Removes the first track that has the given title from this list.
     *  If such a track is not found, or the list is empty, or the given index
     *  is negative or too big for this list, does nothing. */
    public void remove(String title) {
        int index = indexOf(title);
        if (index != -1) {
            remove(index);
        }
    }

    /** Removes the first track from this list. If the list is empty, does nothing. */
    public void removeFirst() {
        if (size != 0){
            remove(0);
        }
    }
    
    /** Adds all the tracks in the other list to the end of this list. 
     *  If the total size of both lists is too large, does nothing. */
    //// An elegant and terribly inefficient implementation.
     public void add(PlayList other) {
        if ((this.size + other.size) <= this.maxSize){
            for (int i = 0; i < other.size ; i++){
                this.add(other.tracks[i]);
            }
        }
    }

    /** Returns the index in this list of the track that has the shortest duration,
     *  starting the search in location start. For example, if the durations are 
     *  7, 1, 6, 7, 5, 8, 7, then min(2) returns 4, since this the index of the 
     *  minimum value (5) when starting the search from index 2.  
     *  If start is negative or greater than size - 1, returns -1.
     */
    public int minIndex(int start) {
        if ( start < 0 || start > size -1) {
            return -1;
        } else { 
            Track track = tracks[start];
            int indexMin = start;
            for (int i = start + 1; i < this.size ; i++) { 
                if (tracks[i].isShorterThan(track)){
                    track = tracks[i];
                    indexMin = i;
                }
            }
            return indexMin;

        }
        
    }

    /** Returns the title of the shortest track in this list. 
     *  If the list is empty, returns null. */
    public String titleOfShortestTrack() {
        return tracks[minIndex(0)].getTitle();
    }

    /** Sorts this list by increasing duration order: Tracks with shorter
     *  durations will appear first. The sort is done in-place. In other words,
     *  rather than returning a new, sorted playlist, the method sorts
     *  the list on which it was called (this list). */
    public void sortedInPlace() {
        // Uses the selection sort algorithm,  
        // calling the minIndex method in each iteration.
        //// replace this statement with your code
        for( int i = 0; i < size ; i++){
            int indexMin = minIndex(i);
            Track tempTrack = tracks[i];
            tracks[i] = tracks[indexMin];
            tracks[indexMin]= tempTrack;   

        }
    }
            

    
        
}
