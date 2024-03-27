#n(물품의 개수), k(최대 무게) 입력받기
n,k=map(int, input().split())

#각 물품에 대해서 w(무게), v(가치)입력받기
wt=[0]*(n+1)
val=[0]*(n+1)

for i in range(1,n+1):
    wt[i],val[i]=map(int,input().split())

#K 배열 만들기
K=[[0]*(k+1) for _ in range(n+1)]



#배낭의 용량이 0일때   (이미 K배열 선언할 때 0으로 초기화해서 없어도 되긴 함)
for i in range(n+1):
    K[i][0]=0

#물건이 0일때 (어떤 물건도 고려하지 않을 때) (이미 K배열 선언할 때 0으로 초기화해서 없어도 되긴 함)
for w in range(k+1):
    K[0][w]=0


for i in range(n+1):
    for w in range(k+1): 
        if (wt[i]>w):
            K[i][w]=K[i-1][w]
        else:
            K[i][w]=max(K[i-1][w], K[i-1][w-wt[i]]+val[i])


print(K[n][k])