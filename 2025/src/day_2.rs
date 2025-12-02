fn range_iter() -> impl Iterator<Item = i64> {
    let input = include_str!(concat!(
        env!("CARGO_MANIFEST_DIR"),
        "\\src\\day_2.part_1.txt"
    ));

    input
        .trim()
        .split(',')
        .map(|item| {
            let mut range = item.split('-');
            let left: i64 = range.next().unwrap().parse().unwrap();
            let right: i64 = range.next().unwrap().parse().unwrap();

            (left..right + 1).collect::<Vec<_>>()
        })
        .flatten()
}

pub fn part_1() {
    let numbers: i64 = range_iter()
        .filter_map(|item| {
            let str_num = item.to_string();
            // If the length of the number can't be split we know the left will not equal the right
            if str_num.len() % 2 != 0 {
                return None;
            }

            let half = str_num.len() / 2;
            if str_num[0..half] != str_num[half..] {
                return None;
            }

            Some(item)
        })
        .sum();

    println!("Result Part 1: {:?}", numbers);
}

pub fn part_2() {
    let numbers: i64 = range_iter()
        .filter_map(|item| {
            let item_str = item.to_string();
            let str_num = item_str.as_str().as_bytes();

            for i in 1..(str_num.len() / 2) + 1 {
                // First check to see if the item can be chunked evenly by `i`
                if str_num.len() % i != 0 {
                    continue;
                }

                let chunks = str_num.chunks_exact(i);
                let first = chunks.clone().next().unwrap();

                // Test to see if all the chunks match the first item.
                let len = chunks.clone().take_while(|i| i == &first).count();
                if len == chunks.len() {
                    return Some(item);
                }
            }

            None
        })
        .sum();

    println!("Result Part 1: {:?}", numbers);
}
