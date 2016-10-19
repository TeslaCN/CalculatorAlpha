/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculatorPackage;

/**
 * ��˹��Ԫ�����nԪ(nС��10000)һ�η�����
 * @author CJY
 * @param n �������Ԫ�ķ�����
 * @param A A��һ��n*(n+1)��Double�;���,����ÿһ��ǰn��Ԫ�ش�����k1*a1+k2*a2+...+kn*an=c�е�ϵ��k1~kn,ÿһ�е�n+1��Ԫ�ش�����c
 * --------------------------
 * ����ɹ������е�solves�����е�1~n�±�(ע�ⲻ��0��n-1)�ᱣ��δ֪���Ľ�
 */
public class SolutionOfLinearEquations {
    private int flag,n;
    private Double A[][];
    Double solves[];
    /**
     *
     * @param n �������Ԫ�ķ�����
     * @param A A��һ��n*(n+1)��Double�;���,����ÿһ��ǰn��Ԫ�ش�����k1*a1+k2*a2+...+kn*an=c�е�ϵ��k1~kn,ÿһ�е�n+1��Ԫ�ش�����c
     */
    public SolutionOfLinearEquations(int n, Double[][] A) {
        this.n = n;
//        this.A = A;
        this.A=new Double[n+1][n+2];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, this.A[i+1], 1, A[i].length);
        solves=new Double[n+1];
        solve();
    }
    private Double largestInTheColumn(int k){
        Double max=0.0;
        for (int i = k; i <= n; i++) {
            if (Math.abs(A[i][k])>max) {
                max=Math.abs(A[i][k]);
                flag=i;
            }
        }
        return max;
    }
    private void exchange(int r,int k){
        for (int i = 1; i <= n + 1; i++)
            A[0][i] = A[r][i];
        for (int i = 1; i <= n + 1; i++)
            A[r][i] = A[k][i];
        for (int i = 1; i <= n + 1; i++)
            A[k][i] = A[0][i];
    }
    private void solve() {
        Double ark;
        for (int k = 1; k <= n - 1; k++) {
            ark = largestInTheColumn(k);
            if (ark == 0) /*�жϷ����Ƿ�Ϊ���Է��̣����Ƿ�Ϸ�*/ {
                System.out.println("\n�˷����鲻�Ϸ�!");
            } else if (flag != k) {
                exchange(flag, k);
            }
            for (int i = k + 1; i <= n; i++) {
                for (int j = k + 1; j <= n + 1; j++) {
                    A[i][j] -= A[k][j] * A[i][k] / A[k][k];
                }
            }
        }
        solves[n] = A[n][n + 1] / A[n][n];
        for (int k = n - 1; k >= 1; k--) {
            Double me = 0.0;
            for (int j = k + 1; j <= n; j++) {
                me += A[k][j] * solves[j];
            }
            solves[k] = (A[k][n + 1] - me) / A[k][k];
        }
    }
    public static void main(String[] args) {
        Double[][] b={{2.0,1.0,1.0},{0.0,0.0,0.0}};
        SolutionOfLinearEquations temp=new SolutionOfLinearEquations(2, b);
        System.out.println(temp.solves[1]);
        System.out.println(temp.solves[2]);
        System.out.println(temp.solves);
    }
}
