package jp.co.seattle.application;
import java.util.Random;
public class Alugo {


/*ＧＡによるナップザック問題解決プログラム*/
/*ナップザック問題とは、重量と価値の異なるN個の品物があり、そのうちM個を重量Wまで耐えられる袋に詰めるとき、価値の合計を最大にする品物の組合せを探し出す問題である*/
/*以下の個体数、世代数、交叉率、突然変異率等の変数（パラメータ）を様々に変えてＧＡの性能がどう変わるかを実験してみる*/



final static int OMOSA_MAX= 100;//ナップザックの積載上限
final static int GENE_LEN= 10;//ナップザックのサイズ

final static int ITEM_OMOSA_MAX= 15;
final static int ITEM_MAX= 20;//登場人物総数

final static int KOTAISUU= 100;
final static int SEDAI_MAX= 100;
final static int SEDAI_REC= 10;//何世代ごとにエリート（その世代で最優秀の個体）の点数を書き出すかを定義

final static double CROS_RATE= 0.95;//交叉率
final static double MUTA_RATE= 0.05;//突然変異率

public static void main(String[] args){
	arugorizumu();
}

//ここからメイン関数
 public static void arugorizumu () {

     //Randomクラスのインスタンス化
     Random rnd = new Random();

	//変数宣言
	int i , j , m;   //計算用のカウンター変数
	int counter;     //世代
	int[] omosa= new int[ITEM_MAX];
	int[][] gene = new int[KOTAISUU][GENE_LEN];    //個体群を表す配列
	double[] seiseki=new double[KOTAISUU];    //（微妙な差も扱えるように、小数点以下も扱えるダブル型変数とする）

	//ルーレット
	int[][] temp_gene= new int[KOTAISUU][GENE_LEN];
	double total , rulet_value , rulet_cf;
	int rulet_num=0;

	//交叉
	int pos;                     //切断点
	int temp;                    //入替用変数
	double d_ret;                //交叉及び突然変異で用いる変数

	//エリート
	double best_seiseki;
	int elite_num;
	int[] elite= new int[GENE_LEN];

	//[0-1]まずこのケースにおける各品物の重さを決める（乱数で決める）。この条件で最も合計価値の高い品物の組合せを探索する
	for (i = 0 ; i < ITEM_MAX ; i ++) {
		omosa[i] = rnd.nextInt (ITEM_OMOSA_MAX);
		System.out.print(":"+omosa[i]);
	}System.out.println("");

	//[0-2]第一世代の個体群（初期個体群）を出鱈目に生成する（乱数発生関数を用いる）。解を表す数字列はこの場合、品物番号の羅列である
	for (i = 0 ; i < KOTAISUU ; i ++) {
		//詰める品物の数だけ、品物番号を割り当てる繰り返し処理
		for (j = 0 ; j < GENE_LEN ; j ++) {
			//２０個の品物の番号（０～１９）のどれかが出鱈目に割り当てられる（ここでは重複して同じ品物を選ぶ事を許している）
			gene[i][j] = rnd.nextInt (ITEM_MAX);
		}
	}

	//[1]無限に処理を繰り返す命令。ただし、世代数が経過したらブレーク文で抜け出せる
	counter = 0;
	while (true) {
		//[1-1]成績の算出
		for (i = 0 ; i < KOTAISUU ; i ++) {
			seiseki[i] = 0.0;
			for (j = 0 ; j < GENE_LEN ; j ++) {
				seiseki[i] += omosa[gene[i][j]];
			}
			//ただし、袋が耐えられる重さを超えている組合せは最低点（０点）にする
			if (seiseki[i] > OMOSA_MAX) {
				seiseki[i] = 0.0;
			}
		}
		//[1-2]この世代の中でもっとも優秀な固体のindexを獲得し、エリートを退避させる。
		elite_num = 0;
		best_seiseki = seiseki[0];
		for (i = 1 ; i < KOTAISUU ; i ++) {
			if (best_seiseki < seiseki[i]) {
				elite_num = i;
				best_seiseki = seiseki[i];
			}
		}
		for (i = 0 ; i < GENE_LEN ; i ++) {
			elite[i] = gene[elite_num][i];
		}

		//[1-3]ルーレット選択に入る。まずその世代の全ての個体の成績の総和を求める
		total = 0.0;
		for (i = 0 ; i < KOTAISUU ; i ++) {
			total += seiseki[i];
		}
		for (i = 0 ; i < KOTAISUU ; i ++) {
			//０～１の間で、小数点以下４位迄の精度でランダムに少数をとり、これを矢の当たる位置とする。それに成績の総和を掛ける
			rulet_cf = (double) (rnd.nextInt (10000)) / 10000.0 * total;
			rulet_value = 0.0;
			for (j = 0 ; j < KOTAISUU ; j ++) {
				rulet_value += seiseki[j];
				if (rulet_value > rulet_cf) {
					rulet_num = j;
					break;
				}
			}
			//仮の個体群配列に選択された個体を入れる
			for (j = 0 ; j < GENE_LEN ; j ++) {
				temp_gene[i][j] = gene[rulet_num][j];
			}
		}
		//個体数分、ルーレット選択が終わったところで、仮の個体群配列を真の個体群配列にコピーして、次世代の個体群を得る
		for (i = 0 ; i < KOTAISUU ; i ++) {
			for (j = 0 ; j < GENE_LEN ; j ++) {
				gene[i][j] = temp_gene[i][j];
			}
		}

		//[1-4]交叉に入る。番号の若い個体から順に２つずつペアとし、各ペアについて交叉率に従って交叉をする（ここでは、交叉率９５％なので、２０組に１組の確率で交叉をしないペアが現れる）
		//各ペアごとに処理するので、個体群の配列を２つずつ見ていく
		for (i = 0 ; i < KOTAISUU ; i += 2) {
			//まずそのペアが交叉を行うかどうかを決めるため、小数点以下２桁までの０～１の間の少数をランダムにとる
			d_ret = (double) rnd.nextInt (101) / 100.0;
			//その値が交叉率より低ければ交叉を行う
			if (d_ret < CROS_RATE) {
				//一点交叉（切断点が一つだけの交叉）における切断点をランダムに決定する（遺伝子の長さ分、切断点の候補はある）
				pos = rnd.nextInt (GENE_LEN);
				//一点交叉は切断点より後ろの遺伝子を入れ替えればよいので、for文はposの位置から始まる
				for (j = pos ; j < GENE_LEN ; j ++) {
					//ペアのうち番号の若い遺伝子のjの位置の遺伝子座の持つ値を入替用変数に待避させる
					temp = gene[i][j];
					//番号の若い遺伝子のjの位置の遺伝子座に、相手の遺伝子の同じ位置の遺伝子座の値を代入する
					gene[i][j] = gene[i + 1][j];
					//相手の遺伝子のjの位置の遺伝子座に、待避させておいた番号の若い方の遺伝子のjの位置の遺伝子座の値を代入することでjの位置の入れ替終了
					gene[i + 1][j] = temp;
				}
			}
		}
		//[1-5]突然変異に入る。突然変異は全個体の全ての遺伝子座に対して、突然変異率に従って行う（この場合、突然変異率は５％に設定しているので、２０個の遺伝子座のうち１個の割合で変化を受ける）
		for (i = 0 ; i < KOTAISUU ; i ++) {
			//各個体について全ての遺伝子座を見る
			for (j = 0 ; j < GENE_LEN ; j ++) {
				//この遺伝子座が突然変異を受けるかどうかを決めるため、小数点以下２桁までの０～１の間の少数をランダムにとる
				d_ret = (double) rnd.nextInt (101) / 100.0;
				//その値が突然変異率より低ければ突然変異を行う
				if (d_ret < MUTA_RATE) {
					//突然変異は、初期個体群生成のときに代入したランダムな値を、そこの遺伝子座に代入する
					gene[i][j] = rnd.nextInt(ITEM_MAX);
				}
			}
		}
		//[1-6]以上で、進化の為の操作を終わる。待避しておいた前世代のエリートを新しい世代の最初の番号の個体として戻す
		for (i = 0 ; i < GENE_LEN ; i ++) {
			gene[0][i] = elite[i];
		}

		//[1-7]定期的な中途経過報告
		counter ++;
		if (counter % SEDAI_REC == 0) {
			System.out.println("counter = "+ counter+": best = "+best_seiseki);
		}

		if (counter == SEDAI_MAX) {
			break;
		}
	}
	//[3]最優秀な固体の出力
	for (i = 0 ; i < GENE_LEN ; i ++) {
		System.out.print(":" + gene[0][i]);
	}
}
}
