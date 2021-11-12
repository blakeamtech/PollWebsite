Team members: Alexander Coricovac, Matthew Pan 40135588, Alessandro Ciotola 40095354

Github Link: https://github.com/CoricovMA/Assignment1

This is a Poll System where any user is able to anonymously vote for any of the options in a poll that is released to the public and provides Poll Managers with the appropriate tools for viewing information about polls that have been released or closed.

# Version 1.0:
Created a business layer for our poll which allows us to:
- Create poll, update poll, clear poll, close poll, run poll, release poll, unrelease poll, vote in a poll, view poll results, download poll details.
- Done using servlets with various HTTP Methods (GET, POST, PUT, OPTIONS).

Do error handling if a user or poll manager attempts to perform an unimplemented action.

Supply a front-end for the following:
- A voting page, a Poll manager menu, a section to create/update polls.

# Version 2.0:
Updated business layer to include:
- pre-defined users
- users can create polls (access polls, vote, request PIN#)
- New poll manager options (access polls, manage polls using PIN#)

Database which holds: 
- User data, poll data, configuration

Output for XML and JSON

Updated front-end to include:
- unimplemented features (sign in/register)
- new user/poll manager options
