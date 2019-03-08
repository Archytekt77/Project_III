package strategyGames;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import config.StrategyGamesGetPropertyValues;

public class Mastermind extends Games {

	public Mastermind() {

	}

	public Mastermind(StrategyGamesGetPropertyValues prop) {
		super(prop);
		sc = new Scanner(System.in);

	}

	@Override
	protected int[] computerAlgo(String s) {
		int total = rightPlace + wrongPlace;
		List<Integer> tmp = new ArrayList<>();
		for (int v : computerList.get(0)) {
			tmp.add(v);
		}

		if (total == 0)
			computerList = removeUselessElements(computerList, secretHumanTable);
		else if (total == 1)
			computerList = resizeList(extractSingle(tmp), computerList, 1);
		else if (total == 2)
			computerList = resizeList(extractTwofold(tmp), computerList, 2);
		else if (total == 3)
			computerList = resizeList(extractThreefold(tmp), computerList, 3);
		else if (total == 4)
			computerList = resizeList(extractFourfold(tmp), computerList, 4);
		else if (total == 5)
			computerList = resizeList(extractFivefold(tmp), computerList, 5);
		else if (total == 6)
			computerList = resizeList(extractSixfold(tmp), computerList, 6);
		else if (total == 7)
			computerList = resizeList(extractSevenfold(tmp), computerList, 7);
		else if (total == 8)
			computerList = resizeList(extractEightfold(tmp), computerList, 8);
		else if (total == 9)
			computerList = resizeList(extractNinefold(tmp), computerList, 9);
		else if (total == 10)
			computerList = resizeList(extractTenfold(tmp), computerList, 10);

		answerComputerTable = computerList.get(0);

		return answerComputerTable;
	}

	protected List<int[]> removeUselessElements(List<int[]> lst, int[] response) {
		int[] cmp = bufferTab(lst.get(0));
		List<int[]> ret = new ArrayList<>();
		lst.remove(0);

		for (int i = 0; i < lst.size(); i++) {
			boolean flag = true;
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					if (cmp[k] == lst.get(i)[j]) {
						flag = false;
					}
				}
			}
			if (flag == true)
				ret.add(lst.get(i));
		}
		return ret;
	}

	protected List<int[]> resizeList(List<List<Integer>> toSearch, List<int[]> lst, int len) {
		List<int[]> ret = new ArrayList<>();
		lst.remove(0);

		for (int i = 0; i < lst.size(); i++) {
			int count = 0;
			int[] cmp = bufferTab(lst.get(i));

			for (List<Integer> l : toSearch) {
				for (Integer j : l) {
					for (int k = 0; k < cmp.length; k++) {
						if (j == cmp[k]) {
							count++;
							cmp[k] = -1;
						}
					}
				}
			}
			if (count >= len && !ret.contains(lst.get(i)))
				ret.add(bufferTab(lst.get(i)));
		}
		return ret;
	}

	protected List<List<Integer>> extractSingle(List<Integer> l) {
		List<List<Integer>> ret = new ArrayList<>();
		List<Integer> v = new ArrayList<>();

		for (Integer a : l) {
			v.add(a);
		}

		ret.add(v);
		return ret;
	}

	protected List<List<Integer>> extractTwofold(List<Integer> lst) {
		List<List<Integer>> val = new ArrayList<>();

		for (int i1 = 0; i1 < lst.size(); i1++) {
			for (int i2 = i1 + 1; i2 < lst.size(); i2++) {
				List<Integer> add = new ArrayList<>();
				add.add(lst.get(i1));
				add.add(lst.get(i2));
				val.add(add);
			}
		}

		return val;
	}

	protected List<List<Integer>> extractThreefold(List<Integer> lst) {
		List<List<Integer>> val = new ArrayList<>();

		for (int i1 = 0; i1 < lst.size(); i1++) {
			for (int i2 = i1 + 1; i2 < lst.size(); i2++) {
				for (int i3 = i2 + 1; i3 < lst.size(); i3++) {
					List<Integer> add = new ArrayList<>();
					add.add(lst.get(i1));
					add.add(lst.get(i2));
					add.add(lst.get(i3));
					val.add(add);
				}
			}
		}
		return val;
	}

	protected List<List<Integer>> extractFourfold(List<Integer> lst) {
		List<List<Integer>> val = new ArrayList<>();

		for (int i1 = 0; i1 < lst.size(); i1++) {
			for (int i2 = i1 + 1; i2 < lst.size(); i2++) {
				for (int i3 = i2 + 1; i3 < lst.size(); i3++) {
					for (int i4 = i3 + 1; i4 < lst.size(); i4++) {
						List<Integer> add = new ArrayList<>();
						add.add(lst.get(i1));
						add.add(lst.get(i2));
						add.add(lst.get(i3));
						add.add(lst.get(i4));

						val.add(add);
					}
				}
			}
		}
		return val;
	}

	protected List<List<Integer>> extractFivefold(List<Integer> lst) {
		List<List<Integer>> val = new ArrayList<>();

		for (int i1 = 0; i1 < lst.size(); i1++) {
			for (int i2 = i1 + 1; i2 < lst.size(); i2++) {
				for (int i3 = i2 + 1; i3 < lst.size(); i3++) {
					for (int i4 = i3 + 1; i4 < lst.size(); i4++) {
						for (int i5 = i4 + 1; i5 < lst.size(); i5++) {

							List<Integer> add = new ArrayList<>();
							add.add(lst.get(i1));
							add.add(lst.get(i2));
							add.add(lst.get(i3));
							add.add(lst.get(i4));
							add.add(lst.get(i5));

							val.add(add);
						}
					}
				}
			}
		}
		return val;
	}

	protected List<List<Integer>> extractSixfold(List<Integer> lst) {
		List<List<Integer>> val = new ArrayList<>();

		for (int i1 = 0; i1 < lst.size(); i1++) {
			for (int i2 = i1 + 1; i2 < lst.size(); i2++) {
				for (int i3 = i2 + 1; i3 < lst.size(); i3++) {
					for (int i4 = i3 + 1; i4 < lst.size(); i4++) {
						for (int i5 = i4 + 1; i5 < lst.size(); i5++) {
							for (int i6 = i5 + 1; i6 < lst.size(); i6++) {

								List<Integer> add = new ArrayList<>();
								add.add(lst.get(i1));
								add.add(lst.get(i2));
								add.add(lst.get(i3));
								add.add(lst.get(i4));
								add.add(lst.get(i5));
								add.add(lst.get(i6));

								val.add(add);
							}
						}
					}
				}
			}
		}
		return val;
	}

	protected List<List<Integer>> extractSevenfold(List<Integer> lst) {
		List<List<Integer>> val = new ArrayList<>();

		for (int i1 = 0; i1 < lst.size(); i1++) {
			for (int i2 = i1 + 1; i2 < lst.size(); i2++) {
				for (int i3 = i2 + 1; i3 < lst.size(); i3++) {
					for (int i4 = i3 + 1; i4 < lst.size(); i4++) {
						for (int i5 = i4 + 1; i5 < lst.size(); i5++) {
							for (int i6 = i5 + 1; i6 < lst.size(); i6++) {
								for (int i7 = i6 + 1; i7 < lst.size(); i7++) {

									List<Integer> add = new ArrayList<>();
									add.add(lst.get(i1));
									add.add(lst.get(i2));
									add.add(lst.get(i3));
									add.add(lst.get(i4));
									add.add(lst.get(i5));
									add.add(lst.get(i6));
									add.add(lst.get(i7));

									val.add(add);
								}
							}
						}
					}
				}
			}
		}
		return val;
	}

	protected List<List<Integer>> extractEightfold(List<Integer> lst) {
		List<List<Integer>> val = new ArrayList<>();

		for (int i1 = 0; i1 < lst.size(); i1++) {
			for (int i2 = i1 + 1; i2 < lst.size(); i2++) {
				for (int i3 = i2 + 1; i3 < lst.size(); i3++) {
					for (int i4 = i3 + 1; i4 < lst.size(); i4++) {
						for (int i5 = i4 + 1; i5 < lst.size(); i5++) {
							for (int i6 = i5 + 1; i6 < lst.size(); i6++) {
								for (int i7 = i6 + 1; i7 < lst.size(); i7++) {
									for (int i8 = i7 + 1; i8 < lst.size(); i8++) {

										List<Integer> add = new ArrayList<>();
										add.add(lst.get(i1));
										add.add(lst.get(i2));
										add.add(lst.get(i3));
										add.add(lst.get(i4));
										add.add(lst.get(i5));
										add.add(lst.get(i6));
										add.add(lst.get(i7));
										add.add(lst.get(i8));

										val.add(add);
									}
								}
							}
						}
					}
				}
			}
		}
		return val;
	}

	protected List<List<Integer>> extractNinefold(List<Integer> lst) {
		List<List<Integer>> val = new ArrayList<>();

		for (int i1 = 0; i1 < lst.size(); i1++) {
			for (int i2 = i1 + 1; i2 < lst.size(); i2++) {
				for (int i3 = i2 + 1; i3 < lst.size(); i3++) {
					for (int i4 = i3 + 1; i4 < lst.size(); i4++) {
						for (int i5 = i4 + 1; i5 < lst.size(); i5++) {
							for (int i6 = i5 + 1; i6 < lst.size(); i6++) {
								for (int i7 = i6 + 1; i7 < lst.size(); i7++) {
									for (int i8 = i7 + 1; i8 < lst.size(); i8++) {
										for (int i9 = i8 + 1; i9 < lst.size(); i9++) {

											List<Integer> add = new ArrayList<>();
											add.add(lst.get(i1));
											add.add(lst.get(i2));
											add.add(lst.get(i3));
											add.add(lst.get(i4));
											add.add(lst.get(i5));
											add.add(lst.get(i6));
											add.add(lst.get(i7));
											add.add(lst.get(i8));
											add.add(lst.get(i9));

											val.add(add);
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return val;
	}

	protected List<List<Integer>> extractTenfold(List<Integer> lst) {
		List<List<Integer>> val = new ArrayList<>();

		for (int i1 = 0; i1 < lst.size(); i1++) {
			for (int i2 = i1 + 1; i2 < lst.size(); i2++) {
				for (int i3 = i2 + 1; i3 < lst.size(); i3++) {
					for (int i4 = i3 + 1; i4 < lst.size(); i4++) {
						for (int i5 = i4 + 1; i5 < lst.size(); i5++) {
							for (int i6 = i5 + 1; i6 < lst.size(); i6++) {
								for (int i7 = i6 + 1; i7 < lst.size(); i7++) {
									for (int i8 = i7 + 1; i8 < lst.size(); i8++) {
										for (int i9 = i8 + 1; i9 < lst.size(); i9++) {
											for (int i10 = i9 + 1; i10 < lst.size(); i10++) {

												List<Integer> add = new ArrayList<>();
												add.add(lst.get(i1));
												add.add(lst.get(i2));
												add.add(lst.get(i3));
												add.add(lst.get(i4));
												add.add(lst.get(i5));
												add.add(lst.get(i6));
												add.add(lst.get(i7));
												add.add(lst.get(i8));
												add.add(lst.get(i9));
												add.add(lst.get(i10));

												val.add(add);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return val;
	}

	@Override
	protected String secretComputerTableCheck(int[] tab) {
		String ret = "";
		int[] bufferSecretComputerTable = new int[secretComputerTable.length];
		rightPlace = 0;
		wrongPlace = 0;

		for (int i = 0; i < bufferSecretComputerTable.length; i++)
			bufferSecretComputerTable[i] = secretComputerTable[i];

		for (int i = 0; i < bufferSecretComputerTable.length; i++) {
			if (bufferSecretComputerTable[i] == tab[i]) {
				bufferSecretComputerTable[i] = -1;
				rightPlace++;
			}
		}

		for (int i = 0; i < bufferSecretComputerTable.length; i++) {
			if (bufferSecretComputerTable[i] != -1) {
				for (int j = 0; j < bufferSecretComputerTable.length; j++) {
					if (bufferSecretComputerTable[i] == tab[j]) {
						tab[j] = -1;
						wrongPlace++;
					}
				}
			}
		}

		ret = "Chiffre(s) bien placé(s) " + rightPlace + ", chiffre(s) mal placé(s) " + wrongPlace;
		return ret;

	}

	@Override
	protected String secretHumanTableCheck(int[] tab) {
		String ret = "";
		rightPlace = 0;
		wrongPlace = 0;
		sc = new Scanner(System.in);

		try {
			logger.info("Chiffre(s) bien placé(s) :");
			rightPlace = sc.nextInt();

			logger.info("Chiffre(s) bon(s) mais mal placé(s)");
			wrongPlace = sc.nextInt();

			ret = "Chiffre(s) bien placé(s) " + rightPlace + ", chiffre(s) mal placé(s) " + wrongPlace;
			
			logger.info(ret);

			if (rightPlace + wrongPlace > size)
				throw new Exception();
		} catch (Exception e) {
			logger.error("Vous n'avez pas entré une réponse convenable.");
			sc = new Scanner(System.in);
			secretHumanTableCheck(tab);
		}

		return ret;
	}

	@Override
	protected int[] firstDefaultComputerResponse() {
		answerComputerTable = computerList.get(0);

		return answerComputerTable;
	}

}
