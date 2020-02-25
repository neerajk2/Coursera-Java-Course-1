public class Part3 {

    public int findStopCodon(String dna, int startIndex, String codon) {

        int currIndex = dna.indexOf(codon, startIndex);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(codon, currIndex + 1);
            }
        }
        return -1;
    }

    public String findingGene(String dna, int where) {

        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) return "";
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = 0;

        if (taaIndex == -1 || (tgaIndex != -1 && tagIndex < taaIndex)) {
            minIndex = tagIndex;
        } else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 || (tgaIndex != -1 && tgaIndex < minIndex)) {
            minIndex = tgaIndex;
        }

        if (minIndex == -1) {
            return "";
        }

        return dna.substring(startIndex, minIndex + 3);
    }

    public int countGenes (String dna) {

        int startIndex = 0;
        int count = 0;
        while (true) {
            String currGene = findingGene(dna, startIndex);
            if (currGene.isEmpty()) {
                break;
            }
            //System.out.println("The Current Gene is " + currGene);
            count++;
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();

        }
        return count;
    }

    public static void main(String[] args) {

        Part3 p5 = new Part3();
        String dna = "AATGCATATATAACTATCTAAATCTACGATATATTAAGGCTGATAGATGGCTAATAGTCATGTACATGACTAATAGTAATGATAG";
        System.out.println(p5.countGenes(dna));
    }
}
