import sys
from collections import deque
input = sys.stdin.readline

sum = 0
cnt = 0
n = int(input())
max_height = 0
board = [list(map(int, input().split())) for _ in range(n)]

max_height = max(map(max, board))

def bfs(x, y, k):
    dx, dy = [-1, 0, 1, 0], [0, 1, 0, -1]
    q = deque()
    q.append((x, y))
    
    while q:
        x, y = q.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < n and 0 <= ny < n and visited[nx][ny] == 0 and board[nx][ny] > k:
                visited[nx][ny] = 1
                q.append((nx, ny))


for k in range(max_height):
    visited = [[0] * n for _ in range(n)]
    cnt = 0

    for i in range(n):
        for j in range(n):
            if visited[i][j] == 0 and board[i][j] > k:
                bfs(i, j, k)
                cnt += 1
    sum = max(sum, cnt)

print(sum)