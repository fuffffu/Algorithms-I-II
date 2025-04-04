import java.util.*;

public class DocumentSearch {
    private String[] documentWords;
    private List<String> searchWords;

    public DocumentSearch(String[] documentWords, List<String> searchWords) {
        this.documentWords = documentWords;
        this.searchWords = searchWords;
    }

    public int findShortInterval() {
        // 记录每个查询词在文档中的索引位置
        Map<String, List<Integer>> wordIndices = new HashMap<>();
        for (String word : searchWords) {
            wordIndices.put(word, new ArrayList<>());
        }

        // 填充每个查询词在文档中的位置
        for (int i = 0; i < documentWords.length; i++) {
            String docword = documentWords[i];
            if (wordIndices.containsKey(docword)) {
                wordIndices.get(docword).add(i);
            }
        }

        // 初始化指针，指向每个查询词的位置
        int[] pointers = new int[searchWords.size()];
        Arrays.fill(pointers, 0);
        int minLength = Integer.MAX_VALUE;
        int left = -1, right = -1;

        while (true) {
            int maxIndex = -1, minIndex = Integer.MAX_VALUE;

            // 寻找当前查询词的最大和最小索引
            for (int i = 0; i < searchWords.size(); i++) {
                List<Integer> indices = wordIndices.get(searchWords.get(i));
                if (pointers[i] >= indices.size()) {
                    // 如果某个查询词已经遍历完它的所有位置，返回结果
                    return minLength == Integer.MAX_VALUE ? -1 : minLength;
                }//minLength是max的话意味着没找到，返回-1，找到了返回minLenght
                int currentIndex = indices.get(pointers[i]);
                maxIndex = Math.max(maxIndex, currentIndex);
                minIndex = Math.min(minIndex, currentIndex);
            }

            // 更新最小长度
            minLength = Math.min(minLength, maxIndex - minIndex + 1);

            // 移动指针，指向当前窗口中最小的索引位置
            for (int i = 0; i < searchWords.size(); i++) {
                List<Integer> indices = wordIndices.get(searchWords.get(i));
                if (indices.get(pointers[i]) == minIndex) {
                    pointers[i]++; //通过指针来映射到对应查询词的下一个出现位置
                    //通过映射到 indices 中的位置，进而实现跳到 searchWords 中的下一个查询词的位置。
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] document = {"the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"};
        List<String> query = Arrays.asList("quick", "fox", "lazy");

        DocumentSearch ds = new DocumentSearch(document, query);
        int result = ds.findShortInterval();
        System.out.println("Shortest interval length: " + result); // Example output: 4
    }
}
