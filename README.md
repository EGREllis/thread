# thread
Took an interest in different threading strategies and their performance on my macbook Pro

Really simple task - increment a integer.

Running "broken" basic trial on 8 processors
Running "lock counter" basic trial on 8 processors
Running "lock perform adder" basic trial on 8 processors
Running "lock increment adder" basic trial on 8 processors
Running "concurrent linked transfer queue" concurrent trial on 8 processors
Running "map reduce" concurrent trial on 8 processors
Running "atomic counter" basic trial on 8 processors
| Trial                             | Elapsed time/ms | Processor time/ms | Error rate/pc |
|                        map reduce |              22 |               176 |   0.00000     |
|                lock perform adder |           20005 |               178 |   0.00000     |
|                            broken |           20024 |               228 |   69.4001     |
|                    atomic counter |           20006 |             15341 |   0.00000     |
|                      lock counter |           20005 |             29866 |   0.00000     |
|              lock increment adder |           20009 |             34482 |   0.00000     |
|  concurrent linked transfer queue |           62966 |            502673 |   0.00000     |
