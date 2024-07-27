import sys
from collections import deque

input=sys.stdin.readline

N, K = map(int, input().split())

if N == K:
    print(0)
else:

    q = deque([N])
    visited = [-1] * 100001
    visited[N] = 0

    while q:
        x = q.popleft()

        for next_pos in (x - 1, x + 1, x * 2):
            if 0 <= next_pos <= 100000 and visited[next_pos] == -1:
                visited[next_pos] = visited[x] + 1
                q.append(next_pos)


                if next_pos == K:
                    print(visited[next_pos])
