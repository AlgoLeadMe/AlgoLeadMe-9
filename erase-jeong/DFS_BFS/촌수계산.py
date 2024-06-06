#입력값 받는 부분
n=int(input())
a,b=map(int,input().split())
m=int(input())

#기본 선언
graph=[[] for _ in range(n+1)]  #노드의 번호와 배열 번호 통일시키기 위해서 n이 아닌 n+1 사용
visited=[[False]*(n+1)]
result=[]


for _ in range(m):
    x,y=map(int, input().split())
    graph[x].append(y)
    graph[y].append(x)



def dfs(v,num):
    num+=1
    visited[v]=True

    if v==b:
        result.append(num)
    
    for i in graph[v]:
        if not visited[i]:
            dfs(i,num)

dfs(a,0)

if len(result)==0:
    print(-1)
else:
    print(result)
