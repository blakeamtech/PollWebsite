package Storage;

import Polls.Poll;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PollStore {

    public static class Vote{
        public String pin;
        public String vote;
    }

    private static final Map<String, Set<Vote>> voteStore = new HashMap<>();
    private static final Map<String, Poll> pollStore = new HashMap<>();

    public static void vote(Vote vote, Poll poll){
        pollStore.putIfAbsent(poll.getPollId(), poll);
        if(!voteStore.containsKey(poll.getPollId()))
            voteStore.put(poll.getPollId(), new HashSet<>());
        voteStore.get(poll.getPollId()).add(vote);
    }

    public static void updateVote(Vote vote, Poll poll){
        voteStore.get(poll.getPollId()).add(vote);
    }


}
