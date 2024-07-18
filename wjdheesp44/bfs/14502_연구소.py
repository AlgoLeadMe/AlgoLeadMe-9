import sys
from collections import deque
from itertools import combinations

input = sys.stdin.readline
zero = []

n, m = map(int, input().split())
board = []
for _ in range(n):
    board.append(list(map(int, input().split())))

for i in range(n):
    for j in range(m):
        if board[i][j] == 0:
            zero.append([i, j])

walls = list(combinations(zero, 3))

result = []

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

for wall_idx in range(len(walls)):  # 벽들 조합 순회
    for i in range(3):
        x, y = walls[wall_idx][i]
        board[x][y] = 1
        
    q = deque()
    visited = [[0] * m for _ in range(n)]
    zero_cnt = 0
    for i in range(n):  
        for j in range(m):
            if board[i][j] == 0:
                zero_cnt += 1
            if board[i][j] == 2:    # 바이러스 큐에 저장
                q.append((i, j))

    while q:
        x, y = q.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < n and 0 <= ny < m and not visited[nx][ny] and board[nx][ny] == 0:
                visited[nx][ny] = 1
                q.append((nx, ny))
                zero_cnt -= 1
    result.append(zero_cnt)

    for i in range(3):  # board 다시 돌려놓기
        x, y = walls[wall_idx][i]
        board[x][y] = 0


print(max(result))